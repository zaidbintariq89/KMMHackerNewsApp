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
