//
//  UsageView.swift
//  iosApp
//
//  Created by Kaleem Asad on 18/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct UsageView: View {
    var body: some View {
        VStack{
            CarouselView()
                .frame(maxWidth: .infinity)
                .edgesIgnoringSafeArea(.all)
                .background(Color.white)
                .frame(height: 170)
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

struct BannerView: View {
    let imageName: String
    let title: String
    let description: String
    let url: URL
    
    var body: some View {
        HStack {
            Image(imageName)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 90, height: 90)
                .cornerRadius(20)
            
            VStack(alignment: .leading) {
                Text(title)
                    .font(.body)
                    .padding(.bottom, 2)
                    .foregroundColor(.secondary)
                
                
                Text(description) // Display the description
                    .font(.footnote)
                    .multilineTextAlignment(.leading) // Align text to the left
                    .lineLimit(2)
                //                    .padding(.bottom, 1)
                
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



struct TitleDescriptionView: View {
    @State private var progressValue: Double = 0.6 // Adjust this value as needed
    
    var body: some View {
        VStack {
            Text("2.31 GB")
                .font(.system(size: 52, weight: .bold)) // Customize the font size (32) and weight (bold)
                .foregroundColor(AppColors.appGreen)
                .padding(.bottom, 10)
            
            Text("Remaining Data")
                .font(.title2)
                .foregroundColor(AppColors.appGreen)
                .multilineTextAlignment(.center)
                .padding(.horizontal, 20)
            
            ProgressBar(progress: progressValue)
                .padding(.horizontal, 20) // 20 units of spacing from leading and trailing edges
                .padding(.vertical, 10) // Add vertical padding inside the progress bar
                .shadow(color: Color.black.opacity(0.3), radius: 5, x: 0, y: 5) // Add shadow to the progress bar
        }
        .frame(maxWidth: .infinity)
        .edgesIgnoringSafeArea(.all)
        .background(Color.white) // Add white background color to the entire view
        
    }
}

struct ProgressBar: View {
    let progress: Double
    
    var body: some View {
        GeometryReader { geometry in
            ZStack(alignment: .leading) {
                RoundedRectangle(cornerRadius: 10) // Rounded corners with a radius of 10
                    .foregroundColor(.white)
                    .frame(width: geometry.size.width, height: 20) // Use the full width of the container
                
                RoundedRectangle(cornerRadius: 10) // Rounded corners with a radius of 10
                    .foregroundColor(AppColors.appGreen)
                    .frame(width: CGFloat(self.progress) * (geometry.size.width), height: 20)
            }
        }
        .frame(height: 20) // Set the height to 20
    }
}
