//
//  TitleDescriptionView.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


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
