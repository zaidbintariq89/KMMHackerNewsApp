//
//  SessionsView.swift
//  FullScreenNavigationView
//
//

import SwiftUI
import shared

struct MessagesView: View {
    @ObservedObject private(set) var viewModel: ViewModel

      var body: some View {
          NavigationView {
              listView()
              .navigationBarTitle("SpaceX Launches")
              .navigationBarItems(trailing:
                  Button("Reload") {
                      self.viewModel.loadLaunches(forceReload: true)
              })
          }
      }

      private func listView() -> AnyView {
          switch viewModel.launches {
          case .loading:
              return AnyView(Text("Loading...").multilineTextAlignment(.center))
          case .result(let launches):
              return AnyView(List(launches) { launch in
                  RocketLaunchRow(rocketLaunch: launch)
  //                Text("asd");
              })
          case .error(let description):
              return AnyView(Text(description).multilineTextAlignment(.center))
          }
      }
  }

  extension MessagesView {

      enum LoadableLaunches {
          case loading
          case result([RocketLaunch])
          case error(String)
      }

      @MainActor
      class ViewModel: ObservableObject {
          let sdk: NetworkRepo
          @Published var launches = LoadableLaunches.loading

          init(sdk: NetworkRepo) {
              self.sdk = sdk
              self.loadLaunches(forceReload: false)
          }

          func loadLaunches(forceReload: Bool) {
              Task {
                  do {
                      self.launches = .loading
                      let launches = try await sdk.getLaunches(forceReload: forceReload)
                      self.launches = .result(launches)
                  } catch {
                      self.launches = .error(error.localizedDescription)
                  }
              }
          }
      }
  }

  extension RocketLaunch: Identifiable { }

//{
//    var body: some View {
//        Text("Sessions View!")
//    }
//}
