//
//  ProfileView.swift
//  FullScreenNavigationView
//
//

import SwiftUI

struct MoreView: View {
    var body: some View {
        VStack {
            List {
                ItemWithLeadingImage(title: "What's New?", description: "Check out the latest features and updates to the MyRogers app.", imageName: "arrow.up.forward")
                ItemWithLeadingImage(title: "Subscriptions", description: "Your Description", imageName: "gear")
                ItemWithLeadingImage(title: "Profile", description: "Your Description", imageName: "gear")
                ItemWithLeadingImage(title: "Terms & Conditions", description: nil, imageName: "book")
                // Add more list items as needed
            }
            .listStyle(PlainListStyle()) // Use a plain list style
            .navigationBarTitle("List Example", displayMode: .inline)
            
            Spacer() // Push list items to the top
            
            Text("Version 6.7.0(3)")
                .font(.subheadline)
                .foregroundColor(.gray) // Set text color to light gray
                .frame(maxWidth: .infinity, alignment: .leading) // Align text to the left
                .padding(.leading, 20) // Add left padding
            
            Text("1995-2-23 Rogers Communications")
                .font(.subheadline)
                .foregroundColor(.gray) // Set text color to light gray
                .frame(maxWidth: .infinity, alignment: .leading) // Align text to the left
                .padding(.leading, 20) // Add left padding
        }
    }
}



struct MoreView_Previews: PreviewProvider {
    static var previews: some View {
        MoreView()
    }
}
