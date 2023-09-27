//
//  BannerView.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `BannerView` is a SwiftUI view that displays a banner with an image loaded from a URL, title, description, and a link.
struct BannerView: View {
    let imageName: String
    let title: String
    let description: String
    let url: URL
    
    var body: some View {
        HStack {
            // Banner image loaded from URL
            AsyncImage(url: URL(string: imageName)) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 90, height: 90)
                        .cornerRadius(20)
                case .failure:
                    Image(systemName: "photo")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 90, height: 90)
                        .cornerRadius(20)
                @unknown default:
                    EmptyView()
                }
            }
            
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
