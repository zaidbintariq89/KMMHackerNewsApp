//
//  CardView.swift
//  iosApp
//
//  Created by Kaleem Asad on 23/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `CardView` is a SwiftUI view that displays a card with a title, description, and an optional image.
struct CardView: View {
    /// The title displayed at the top of the card.
    var title: String
    /// The description or content of the card.
    var description: String
    /// The system image name used as an optional icon.
    var imageName: String
    /// The shadow radius applied to the card.
    var shadowRadius: CGFloat = 5.0
    
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            HStack {
                Text(title)
                    .font(.headline)
                
                Spacer()
                
                Image(systemName: imageName)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 15, height: 15)
            }
            
            Text(description)
                .font(.subheadline)
                .foregroundColor(.secondary)
        }
        .padding()
        .background(Color.white)
        .cornerRadius(5)
        .shadow(radius: shadowRadius)
        .padding(.horizontal, 10) // Add 10-point padding to leading and trailing edges
    }
}
