//
//  TabBarItem.swift
//  iosApp
//
//  Created by Kaleem Asad on 23/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `TabBarItem` is a SwiftUI view that represents an individual tab item within a tab bar.
struct TabBarItem: View {
    /// A binding to the currently selected tab.
    @Binding var currentTab: Int
    /// A namespace used for managing animations.
    let namespace: Namespace.ID
    
    /// The name or title of the tab item.
    var tabBarItemName: String
    /// The index of the tab item.
    var tab: Int
    
    var body: some View {
        Button {
            self.currentTab = tab
        } label: {
            VStack {
                Spacer()
                Text(tabBarItemName).foregroundColor(currentTab == tab ? .red : .black)
                if currentTab == tab {
                    Color.red
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
