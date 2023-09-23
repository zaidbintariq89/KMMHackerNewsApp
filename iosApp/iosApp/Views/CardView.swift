//
//  CardView.swift
//  iosApp
//
//  Created by Kaleem Asad on 23/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CardView: View {
    var title: String
    var description: String
    var imageName: String
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
