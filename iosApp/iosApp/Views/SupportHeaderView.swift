//
//  SupportHeaderView.swift
//  iosApp
//
//  Created by Kaleem Asad on 24/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `SupportHeaderView` is a SwiftUI view that displays a header for a support screen.
struct SupportHeaderView: View {
    var body: some View {
        ZStack {
            AppColors.appRed // Set the background color
            
            HStack {
                VStack(alignment: .leading, spacing: 8) { // Align text to the left
                    Text("Ask Anna")
                        .font(.headline)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                    Text("Need Help?")
                        .font(.title)
                        .foregroundColor(.white)
                    
                    Button(action: {
                        // Add your action here for starting a chat
                    }) {
                        Text("Start a chat")
                            .font(.headline)
                            .fontWeight(.medium)
                            .padding(10)
                            .background(Color.white)
                            .cornerRadius(10)
                            .foregroundColor(AppColors.appRed)
                    }
                }
                .padding(.leading, 20) // Adjust the trailing padding as needed
                Spacer()
                Image(systemName: "message.badge")
                    .resizable()
                    .frame(width: 50, height: 50)
                    .foregroundColor(.white)
                    .padding(.trailing, 30)
            }
        }
        .frame(height: 170) // Adjust the height as needed
    }
}
