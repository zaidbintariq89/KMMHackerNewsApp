//
//  UsageViewModel.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension UsageView {
    
    enum LoadableBills {
        case loading
        case result(BillingResponseModel)
        case error(String)
    }
    
    @MainActor
    class ViewModel: ObservableObject {
        let sdk: NetworkRepo
        @Published var billingData = LoadableBills.loading

        init(sdk: NetworkRepo) {
            self.sdk = sdk
            self.loadBillingData()
        }
        
        func loadBillingData() {
            Task {
                do {
                    self.billingData = .loading
                    let _billingData = try await sdk.getBillingData()
                    self.billingData = .result(_billingData)
                } catch {
                    self.billingData = .error(error.localizedDescription)
                }
            }
        }
    }
}
