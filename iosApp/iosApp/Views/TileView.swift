//
//  TileView.swift
//  iosApp
//
//  Created by Kaleem Asad on 24/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `TileView` is a SwiftUI view that represents a tile with an image and a title.
struct TileView: View {
    /// The name of the system image used in the tile.
    let imageName: String
    
    /// The title text to be displayed in the tile.
    let title: String
    
    /// The background color of the tile.
    let backgroundColor: Color
    
    var body: some View {
        GeometryReader { geometry in
            VStack(alignment: .leading, spacing: 10) {
                Image(systemName: imageName)
                    .resizable()
                    .frame(width: 20, height: 20)
                    .padding(.leading, 1)
                
                Text(title)
                    .font(.headline)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            .padding(20)
            .background(backgroundColor)
            .cornerRadius(20)
            .frame(height: 50)
            .frame(width: geometry.size.width)
        }
    }
}
