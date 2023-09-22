//
//  View1.swift
//  iosApp
//
//  Created by Kaleem Asad on 18/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct View1: View {
    var body: some View {
         CarouselView()
            .frame(width: 300, height: 100)
             .padding(EdgeInsets(top: 0.0, leading: 0.0, bottom: 100, trailing: 0.0))// Adjust the height as needed
     }}



struct BannerView: View {
    let imageName: String
    let text: String
    
    var body: some View {
        
        HStack {
            Image(imageName)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 100, height: 100) // Adjust the size as needed
            
            Text(text)
                .font(.title)
                .padding()
        }
    }
}


struct CarouselView: View {
    var body: some View {
        TabView {
            BannerView(imageName: "header", text: "Text 1")
            BannerView(imageName: "header", text: "Text 2")
            BannerView(imageName: "header", text: "Text 3")
            // Add more BannerView instances for additional items
        }
        .tabViewStyle(PageTabViewStyle())
        .indexViewStyle(PageIndexViewStyle(backgroundDisplayMode: .always))
    }
}
