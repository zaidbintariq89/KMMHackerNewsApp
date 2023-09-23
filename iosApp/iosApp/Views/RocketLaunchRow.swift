//
//  RocketLaunchRow.swift
//  iosApp
//
//  Created by Kaleem Asad on 11/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

/// `RocketLaunchRow` is a SwiftUI view that displays information about a rocket launch.
struct RocketLaunchRow: View {
    /// The rocket launch information to display.
    var rocketLaunch: RocketLaunch

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text("Launch name: \(rocketLaunch.missionName)")
                Text(launchText).foregroundColor(launchColor)
                Text("Launch year: \(String(rocketLaunch.launchYear))")
                Text("Launch details: \(rocketLaunch.details ?? "")")
            }
            Spacer()
        }
    }
}

extension RocketLaunchRow {
    /// Computes the text to display for the launch success status.
    private var launchText: String {
        if let isSuccess = rocketLaunch.launchSuccess {
            return isSuccess.boolValue ? "Successful" : "Unsuccessful"
        } else {
            return "No data"
        }
    }

    /// Computes the color for the launch success status.
    private var launchColor: Color {
        if let isSuccess = rocketLaunch.launchSuccess {
            return isSuccess.boolValue ? Color.green : Color.red
        } else {
            return Color.gray
        }
    }
}
