//
//  ItemWithLeadingImage.swift
//  iosApp
//
//  Created by Kaleem Asad on 24/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `ItemWithLeadingImage` is a SwiftUI view that displays an item with an image, title, and optional description.
struct ItemWithLeadingImage: View {
    /// The title of the item.
    let title: String
    /// An optional description of the item.
    let description: String?
    /// The name of the image to display.
    let imageName: String
    /// The color of the image.
    let imageColor: Color?
    
    init(title: String, description: String? = nil, imageName: String, imageColor: Color? = nil) {
        self.title = title
        self.description = description
        self.imageName = imageName
        self.imageColor = imageColor ?? AppColors.appRed // Default to red if color is not provided
    }
    
    var body: some View {
        HStack {
            Image(systemName: imageName)
                .resizable()
                .frame(width: 18, height: 18)
                .padding(.trailing, 10)
                .foregroundColor(imageColor) // Use the provided or default color
            
            VStack(alignment: .leading) {
                Text(title)
                    .font(.bold(.callout)())
                Spacer(minLength: 1)
                
                if let description = description {
                    Text(description)
                        .font(.subheadline)
                        .foregroundColor(Color.gray)
                }
            }
            
            Spacer()
            
            Image(systemName: "chevron.right").foregroundColor(Color.gray)
        }
        .padding(.vertical, 10)
    }
}
