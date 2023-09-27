//
//  ServicesViewModel.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

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
