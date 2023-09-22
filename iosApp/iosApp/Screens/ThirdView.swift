//
//  ThirdView.swift
//  FullScreenNavigationView
//
//

import SwiftUI

struct ThirdView: View {
    var isThirdView: Bool
    
    var body: some View {
        VStack{
            Spacer()
            Text("Third View!")
            Button {
                AppEventsManager.shared.navigateFullScreen.send((false, .empty))
            } label: {
                Text("Back to Home")
            }
            Spacer()
        }
    }
}
