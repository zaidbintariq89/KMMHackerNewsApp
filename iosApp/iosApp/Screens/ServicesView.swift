//
//  HomeView.swift
//  FullScreenNavigationView
//
//

import SwiftUI

struct ServicesView: View {
    
    @State var currentTab: Int = 0
    @State private var showActionSheet = false
    
    fileprivate func accountSelectView() -> some View {
        return VStack {
            CardView(title: "Demo: (416)301-0069", description: "Wireless", imageName: "chevron.down")
        }
        .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10))
        .onTapGesture {
            showActionSheet = true
        }
        .actionSheet(isPresented: $showActionSheet) {
            ActionSheet(
                title: Text("Choose an option"),
                buttons: [
                    .default(Text("Demo: (416) 301-0069")) {
                        // Action to perform when Option 1 is tapped
                        // You can add your code here
                    },
                    .default(Text("Demo: (413) 301-0069")) {
                        // Action to perform when Option 2 is tapped
                        // You can add your code here
                    },
                    .cancel() // This adds a cancel button to dismiss the ActionSheet
                ]
            )
        }
    }
    
    var body: some View {
        VStack(spacing: 10){
            Spacer()
            accountSelectView()
                TabBarView(currentTab: self.$currentTab)
                TabView(selection: self.$currentTab) {
                    UsageView().tag(0)
                    EquipmentView().tag(1)
                    PackageView().tag(2)
                }
                .tabViewStyle(.page(indexDisplayMode: .never))
//                .edgesIgnoringSafeArea(.all)
        }
        .background(Color.gray.opacity(0.2)) // Set the background color to yellow for the entire body
    }
    //    var body: some View {
    //        VStack(spacing: 20){
    //            Spacer()
    //
    //            Text("Home View!")
    //
    //            Button{
    //                AppEventsManager.shared.navigateFullScreen.send((true, FullScreenNavigationViewType.secondView))
    //            } label: {
    //                Text("FullScreen Navigate to SecondView")
    //            }
    //
    //            Button{
    //                AppEventsManager.shared.navigateFullScreen.send((true, FullScreenNavigationViewType.thirdView(true)))
    //            } label: {
    //                Text("FullScreen Navigate to ThirdView")
    //            }
    //
    //            Spacer()
    //        }
    //    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
