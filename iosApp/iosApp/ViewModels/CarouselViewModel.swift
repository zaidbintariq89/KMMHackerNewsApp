//
//  CarouselViewModel.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension CarouselView {
    
    enum LoadableBanners {
        case loading
        case result(PromotionsResponseModel)
        case error(String)
    }
    
    @MainActor
    class ViewModel: ObservableObject {
        let sdk: NetworkRepo
        @Published var promotionsResponse = LoadableBanners.loading
        
        init(sdk: NetworkRepo) {
            self.sdk = sdk
            self.loadBanners()
        }
        
        func loadBanners() {
            Task {
                do {
                    self.promotionsResponse = .loading
                    let promotionsResponse = try await sdk.getPromotions()
                    self.promotionsResponse = .result(promotionsResponse)
                } catch {
                    self.promotionsResponse = .error(error.localizedDescription)
                }
            }
        }
    }
}
