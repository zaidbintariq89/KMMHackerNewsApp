//
//  HomeView.swift
//  FullScreenNavigationView
//
//

import SwiftUI

struct HomeView: View {
    
    @State var currentTab: Int = 0
    var body: some View {
                VStack(spacing: 20){
                    Spacer()
        
        ZStack(alignment: .top) {
            TabView(selection: self.$currentTab) {
                View1().tag(0)
                View2().tag(1)
                View3().tag(2)
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .edgesIgnoringSafeArea(.all)
            
            TabBarView(currentTab: self.$currentTab)
        }
    }
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

struct TabBarView: View {
    @Binding var currentTab: Int
    @Namespace var namespace
    @ScaledMetric var trainCarSpace = 55
    @State private var showActionSheet = false

    
    var tabBarOptions: [String] = ["Usage", "Equipment", "Package"]
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            VStack {
                       Button("Demo: (416) 301-0069") {
                           showActionSheet = true
                       }
                
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
            HStack(spacing: trainCarSpace) {
                ForEach(Array(zip(self.tabBarOptions.indices,
                                  self.tabBarOptions)),
                        id: \.0,
                        content: {
                    index, name in
                    TabBarItem(currentTab: self.$currentTab,
                               namespace: namespace.self,
                               tabBarItemName: name,
                               tab: index)
                    
                })
            }
            .padding(.horizontal)
        }
        .padding()
        .background(Color.white)
        .frame(height: 90)
        .edgesIgnoringSafeArea(.all)
    }
}

struct TabBarItem: View {
    @Binding var currentTab: Int
    let namespace: Namespace.ID
    
    var tabBarItemName: String
    var tab: Int
    
    var body: some View {
        Button {
            self.currentTab = tab
        } label: {
            VStack {
                Spacer()
                Text(tabBarItemName)
                if currentTab == tab {
                    Color.black
                        .frame(height: 2)
                        .matchedGeometryEffect(id: "underline",
                                               in: namespace,
                                               properties: .frame)
                } else {
                    Color.clear.frame(height: 2)
                }
            }
            .animation(.spring(), value: self.currentTab)
        }
        .buttonStyle(.plain)
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
