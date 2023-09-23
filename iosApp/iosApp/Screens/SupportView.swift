//
//  ChatView.swift
//  FullScreenNavigationView
//
//

import SwiftUI

struct SupportView: View {
    var body: some View {
        NavigationView {
            List {
                Section() {
                    Image("header")
                        .resizable()
                              .frame(height: 150)
                              .listRowInsets(EdgeInsets())
                }
                ForEach((0..<2), id: \.self) { index in
                    Section {
                        NavigationLink(destination: Text("aaa")) {
                            Label("Billing & Accounts", systemImage: "capsule")
                        }
                        NavigationLink(destination: Text("aaa")) {
                            Label("Mobile", systemImage: "paintpalette")
                        }
                        NavigationLink(destination: Text("aaa")) {
                            Label("Home phone", systemImage: "paintpalette")
                        }
                        NavigationLink(destination: Text("aaa")) {
                            Label("Internet", systemImage: "paintpalette")
                        }
                        NavigationLink(destination: Text("aaa")) {
                            Label("TV & Streaming", systemImage: "paintpalette")
                        }
                        NavigationLink(destination: Text("aaa")) {
                            Label("Home Monitoring", systemImage: "paintpalette")
                        }
                    }
                }
            }
            .navigationBarTitle("Support")
            .navigationBarTitleDisplayMode(.inline)
        }
        .accentColor(.accentColor)
    }
}


struct ChatView_Previews: PreviewProvider {
    static var previews: some View {
        SupportView()
    }
}
