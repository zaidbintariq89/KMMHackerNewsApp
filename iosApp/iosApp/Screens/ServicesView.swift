//
//  HomeView.swift
//  FullScreenNavigationView
//
//

import SwiftUI
import shared

struct ServicesView: View {
    
    @State var currentTab: Int = 0
    @State private var showActionSheet = false
    @ObservedObject private(set) var viewModel: ViewModel
    
    fileprivate func accountSelectView() -> some View {
        return VStack {
            CardView(title: "Demo: (416)301-0069", description: "Wireless", imageName: "chevron.down")
        }
        .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10))
        .onTapGesture {
            showActionSheet = true
        }
        .actionSheet(isPresented: $showActionSheet) {
            ActionSheet(
                title: Text("Choose an option"),
                buttons: getActionSheetButtons()
            )
        }
    }
    
    func getActionSheetButtons() -> [ActionSheet.Button] {
        var buttons: [ActionSheet.Button] = []
        
        let phoneNumbers = self.viewModel.getAccountList()?.content.accounts.map({ account in
            return account.accountNumber
        }) ?? []//["(416) 301-0069", "(413) 301-0069"]
        
        for phoneNumber in phoneNumbers {
            let button = ActionSheet.Button.default(Text("Demo: \(phoneNumber)")) {
                // Action to perform when the button is tapped
                // You can add your code here
            }
            buttons.append(button)
        }
        
        // Add a cancel button
        let cancelButton = ActionSheet.Button.cancel()
        buttons.append(cancelButton)
        
        return buttons
    }
    var body: some View {
        VStack(spacing: 10){
            Spacer()
            accountSelectView()
            TabBarView(currentTab: self.$currentTab)
            TabView(selection: self.$currentTab) {
                UsageView().tag(0)
                EquipmentView().tag(1)
                PackageView().tag(2)
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            //                .edgesIgnoringSafeArea(.all)
        }
        .background(Color.gray.opacity(0.2)) // Set the background color to yellow for the entire body
    }
    //    var body: some View {
    //        VStack(spacing: 20){
    //            Spacer()
    //
    //            Text("Home View!")
    //
    //            Button{
    //                AppEventsManager.shared.navigateFullScreen.send((true, FullScreenNavigationViewType.secondView))
    //            } label: {
    //                Text("FullScreen Navigate to SecondView")
    //            }
    //
    //            Button{
    //                AppEventsManager.shared.navigateFullScreen.send((true, FullScreenNavigationViewType.thirdView(true)))
    //            } label: {
    //                Text("FullScreen Navigate to ThirdView")
    //            }
    //
    //            Spacer()
    //        }
    //    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


extension ServicesView {
    
    
    enum LoadableAccounts {
        case loading
        case result(AccountsResponseModel)
        case error(String)
    }
    
    @MainActor
    class ViewModel: ObservableObject {
        let sdk: NetworkRepo
        @Published var accountsData = LoadableAccounts.loading
        init(sdk: NetworkRepo) {
            self.sdk = sdk
            self.loadAccounts()
        }
        
        func loadAccounts() {
            Task {
                do {
                    self.accountsData = .loading
                    let _accounts = try await sdk.getAllAccounts()
                    self.accountsData = .result(_accounts)
                } catch {
                    self.accountsData = .error(error.localizedDescription)
                }
            }
        }
        
        func getAccountList() -> AccountsResponseModel?{
            switch self.accountsData {
            case .loading:
                return nil
            case .result(let accountData):
                return accountData
            case .error(_):
                return nil
            }
        }
    }
}


extension AccountsResponseModel: Identifiable { }


