//
//  UsageView.swift
//  iosApp
//
//  Created by Kaleem Asad on 18/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

/// `UsageView` is a SwiftUI view that displays a carousel of `BannerView` items along with a `TitleDescriptionView`.
struct UsageView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    var body: some View {
        VStack {
            // Carousel of BannerView items
            CarouselView(viewModel: CarouselView.ViewModel(sdk: viewModel.sdk))
                .frame(maxWidth: .infinity)
                .edgesIgnoringSafeArea(.all)
                .background(Color.white)
                .frame(height: 170)
            
            // Title and description view
            TitleDescriptionView()
            
            Spacer()
        }
    }
    
}
extension UsageView {
    
    @MainActor
    class ViewModel: ObservableObject {
        let sdk: NetworkRepo
        init(sdk: NetworkRepo) {
            self.sdk = sdk
        }
    }
}


struct UsageView_Previews: PreviewProvider {
    static var previews: some View {
        let sdk = NetworkRepo(databaseDriverFactory: DatabaseDriverFactory())
        UsageView(viewModel: UsageView.ViewModel(sdk: sdk))
    }
}

extension PromotionsResponseModel: Identifiable { }
extension Promotion: Identifiable { }



