//
//  CarouselView.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared


/// `CarouselView` is a SwiftUI view that displays a carousel of `BannerView` items.
struct CarouselView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        return bannersView()
        
    }
    
    private func bannersView() -> AnyView {
        switch viewModel.promotionsResponse {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let promotionsResponse):
            
            return AnyView(TabView {
                ForEach(promotionsResponse.content.promotions){
                    promotion in
                    BannerView(imageName: promotion.image, title: promotion.title, description: promotion.description_, url: (URL(string: promotion.link) ?? URL(string: "https://example.com"))!)
                }
            }
                .tabViewStyle(PageTabViewStyle())
                .indexViewStyle(PageIndexViewStyle(backgroundDisplayMode: .always))
            )
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

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
