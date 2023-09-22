//
//  AppEventsManager.swift
//  FullScreenNavigationView
//
//

import SwiftUI
import Combine

final public class AppEventsManager {
    static let shared = AppEventsManager()
    /// Present a view over the tab bar
    var navigateFullScreen = PassthroughSubject<(Bool, FullScreenNavigationViewType), Never>()

}
