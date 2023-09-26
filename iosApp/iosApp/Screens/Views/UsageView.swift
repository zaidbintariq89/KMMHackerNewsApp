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
//    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        VStack {
            // Carousel of BannerView items
            CarouselView()
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

struct UsageView_Previews: PreviewProvider {
    static var previews: some View {
        UsageView()
    }
}


/// `BannerView` is a SwiftUI view that displays a banner with an image, title, description, and a link.
struct BannerView: View {
    let imageName: String
    let title: String
    let description: String
    let url: URL
    
    var body: some View {
        HStack {
            // Banner image
            Image(imageName)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 90, height: 90)
                .cornerRadius(20)
            
            VStack(alignment: .leading) {
                // Banner title
                Text(title)
                    .font(.body)
                    .padding(.bottom, 2)
                    .foregroundColor(.secondary)
                
                // Banner description with multiline support
                Text(description)
                    .font(.footnote)
                    .multilineTextAlignment(.leading)
                    .lineLimit(2)
                
                // Banner link
                Link("Open Link", destination: url)
                    .foregroundColor(.blue)
            }
            .padding()
        }
        .frame(height: 120)
        .frame(width: 370)
        .background(Color.white)
        .padding()
    }
}

/// `CarouselView` is a SwiftUI view that displays a carousel of `BannerView` items.
struct CarouselView: View {
    var body: some View {
        TabView {
            BannerView(imageName: "bannerAdd", title: "Great Plans Great Price", description: "Make More Possible with a Perfect plan", url: URL(string: "https://example.com")!)
            BannerView(imageName: "bannerAdd", title: "Great Plans Great Price", description: "Make More Possible with a Perfect plan. Make More Possible with a Perfect plan", url: URL(string: "https://example.com")!)
            BannerView(imageName: "bannerAdd", title: "Great Plans Great Price", description: "Make More Possible with a Perfect plan", url: URL(string: "https://example.com")!)
            // Add more BannerView instances for additional items
        }
        .tabViewStyle(PageTabViewStyle())
        .indexViewStyle(PageIndexViewStyle(backgroundDisplayMode: .always))
    }
}

/// `TitleDescriptionView` is a SwiftUI view that displays a title, description, and a progress bar.
struct TitleDescriptionView: View {
    @State private var progressValue: Double = 0.6 // Adjust this value as needed
    
    var body: some View {
        VStack {
            // Title text with customized font size and weight
            Text("2.31 GB")
                .font(.system(size: 52, weight: .bold))
                .foregroundColor(AppColors.appGreen)
                .padding(.bottom, 10)
            
            // Description text
            Text("Remaining Data")
                .font(.title2)
                .foregroundColor(AppColors.appGreen)
                .multilineTextAlignment(.center)
                .padding(.horizontal, 20)
            
            // Progress bar with padding and shadow
            ProgressBar(progress: progressValue)
                .padding(.horizontal, 20)
                .padding(.vertical, 10)
                .shadow(color: Color.black.opacity(0.3), radius: 5, x: 0, y: 5)
        }
        .frame(maxWidth: .infinity)
        .edgesIgnoringSafeArea(.all)
        .background(Color.white)
    }
}

/// `ProgressBar` is a SwiftUI view that displays a progress bar with rounded corners.
struct ProgressBar: View {
    let progress: Double
    
    var body: some View {
        GeometryReader { geometry in
            ZStack(alignment: .leading) {
                RoundedRectangle(cornerRadius: 10)
                    .foregroundColor(.white)
                    .frame(width: geometry.size.width, height: 20)
                
                RoundedRectangle(cornerRadius: 10)
                    .foregroundColor(AppColors.appGreen)
                    .frame(width: CGFloat(self.progress) * (geometry.size.width), height: 20)
            }
        }
        .frame(height: 20)
    }
}
