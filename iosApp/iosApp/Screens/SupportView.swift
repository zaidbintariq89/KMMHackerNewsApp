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
                    TileView(imageName: "checkmark.circle", title: "Device Toolbox", backgroundColor: .orange)
                    
                    // Second Tile
                    TileView(imageName: "network", title: "Network Aid", backgroundColor: .orange)
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 30) // Add 30-point leading and trailing padding
                .frame(height: 50)
                
                NavigationView {
                    List {
                        ForEach((0..<2), id: \.self) { index in
                            Section {
                                NavigationLink(destination: Text("aaa")) {
                                    Label("Billing & Accounts", systemImage: "capsule")
                                }
                                NavigationLink(destination: Text("aaa")) {
                                    Label("Mobile", systemImage: "paintpalette")
                                }
                                NavigationLink(destination: Text("aaa")) {
                                    Label("Home phone", systemImage: "paintpalette")
                                }
                                NavigationLink(destination: Text("aaa")) {
                                    Label("Internet", systemImage: "paintpalette")
                                }
                                NavigationLink(destination: Text("aaa")) {
                                    Label("TV & Streaming", systemImage: "paintpalette")
                                }
                                NavigationLink(destination: Text("aaa")) {
                                    Label("Home Monitoring", systemImage: "paintpalette")
                                }
                            }
                        }
                    }
                    //                    .navigationBarTitle("Support")
                    .navigationBarTitleDisplayMode(.inline)
                }
                .accentColor(.accentColor)
            }
        }
    }
}

struct ChatView_Previews: PreviewProvider {
    static var previews: some View {
        SupportView()
    }
}
