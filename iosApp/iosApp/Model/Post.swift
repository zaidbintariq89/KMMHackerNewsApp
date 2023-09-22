//
//  Post.swift
//  iosApp
//
//  Created by Kaleem Asad on 11/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct Post: Codable, Identifiable {
    let id: Int
    let title: String
    let body: String
}
