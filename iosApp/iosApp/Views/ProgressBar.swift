//
//  ProgressBar.swift
//  iosApp
//
//  Created by Kaleem Asad on 27/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


/// `ProgressBar` is a SwiftUI view that displays a progress bar with rounded corners.
struct ProgressBar: View {
    let progress: Double
    
    var body: some View {
        GeometryReader { geometry in
            ZStack(alignment: .leading) {
                RoundedRectangle(cornerRadius: 10)
                    .foregroundColor(.white)
                    .frame(width: geometry.size.width, height: 20)
                
                RoundedRectangle(cornerRadius: 10)
                    .foregroundColor(AppColors.appGreen)
                    .frame(width: CGFloat(self.progress) * (geometry.size.width), height: 20)
            }
        }
        .frame(height: 20)
    }
}
