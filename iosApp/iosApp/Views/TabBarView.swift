//
//  TabBarView.swift
//  iosApp
//
//  Created by Kaleem Asad on 23/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
struct TabBarView: View {
    @Binding var currentTab: Int
    @Namespace var namespace
    @ScaledMetric var trainCarSpace = 70
    
    var tabBarOptions: [String] = ["Usage", "Equipment", "Package"]
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            
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
        //        .padding()
        .background(Color.white)
        .frame(height: 50)
        .edgesIgnoringSafeArea(.all)
    }
}
