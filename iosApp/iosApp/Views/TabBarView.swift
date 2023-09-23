//
//  TabBarView.swift
//  iosApp
//
//  Created by Kaleem Asad on 23/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `TabBarView` is a SwiftUI view that displays a horizontal scrollable tab bar with options.
struct TabBarView: View {
    /// A binding to the currently selected tab.
    @Binding var currentTab: Int
    /// A namespace for managing animations.
    @Namespace var namespace
    /// A scaled metric for spacing between tab bar items.
    @ScaledMetric var trainCarSpace = 70
    
    /// The options to display in the tab bar.
    var tabBarOptions: [String] = ["Usage", "Equipment", "Package"]
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: trainCarSpace) {
                ForEach(Array(zip(self.tabBarOptions.indices, self.tabBarOptions)), id: \.0) { index, name in
                    TabBarItem(currentTab: self.$currentTab, namespace: namespace.self, tabBarItemName: name, tab: index)
                }
            }
            .padding(.horizontal)
        }
        .background(Color.white)
        .frame(height: 50)
        .edgesIgnoringSafeArea(.all)
    }
}
