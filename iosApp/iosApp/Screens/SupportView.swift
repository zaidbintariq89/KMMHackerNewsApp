//
//  ChatView.swift
//  FullScreenNavigationView
//
//
import SwiftUI

struct SupportView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 30) {
                SupportHeaderView()
                
                Text("Tools")
                    .font(.title)
                    .fontWeight(.regular)
                    .padding(.leading, 30) // Add padding to align with the tiles
                
                HStack {
                    // First Tile with Gray Background
                    TileView(imageName: "checkmark.circle", title: "Device Toolbox", backgroundColor: AppColors.appDarkGray)
                    
                    // Second Tile
                    TileView(imageName: "network", title: "Network Aid", backgroundColor: AppColors.appDarkGray)
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 30) // Add 30-point leading and trailing padding
                .frame(height: 50)
                
                Text("Support Topics")
                    .font(.title)
                    .fontWeight(.regular)
                    .padding(.leading, 30)
                    .padding(.top, 30)
                Divider() // Add a separator between items
                
                LazyVStack(spacing: 5) {
                    ItemWithLeadingImage(title: "Billing & Accounts", imageName: "book", imageColor: Color.gray)
                    Divider() // Add a separator between items
                    ItemWithLeadingImage(title: "Mobile", imageName: "iphone.circle", imageColor: Color.gray)
                    Divider() // Add a separator between items
                    ItemWithLeadingImage(title: "Home Phone", imageName: "iphone.circle", imageColor: Color.gray)
                    Divider() // Add a separator between items
                    ItemWithLeadingImage(title: "Internet", imageName: "internaldrive", imageColor: Color.gray)
                    Divider() // Add a separator between items
                    ItemWithLeadingImage(title: "TV & Streaming", imageName: "tv", imageColor: Color.gray)
                    Divider() // Add a separator between items
                    // Add more list items as needed
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 30) // Add horizontal padding
                HStack {
                    // First Tile with Gray Background
                    TileView(imageName: "checkmark.circle", title: "Device Toolbox", backgroundColor: AppColors.appRed)
                    
                    // Second Tile
                    TileView(imageName: "network", title: "Network Aid", backgroundColor: AppColors.appRed)
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 30) // Add 30-point leading and trailing padding
                .frame(height: 50)
                HStack {
                    // First Tile with Gray Background
                    TileView(imageName: "checkmark.circle", title: "Device Toolbox", backgroundColor: AppColors.appRed)
                    
                    // Second Tile
                    TileView(imageName: "network", title: "Network Aid", backgroundColor: AppColors.appRed)
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 30) // Add 30-point leading and trailing padding
                .frame(height: 50)
                .padding(.top, 30)
                HStack {
                    // First Tile with Gray Background
                    TileView(imageName: "checkmark.circle", title: "Device Toolbox", backgroundColor: AppColors.appRed)
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 30) // Add 30-point leading and trailing padding
                .frame(height: 50)
                .padding(.top, 30)
            }
        }
    }
}


struct ChatView_Previews: PreviewProvider {
    static var previews: some View {
        SupportView()
    }
}
