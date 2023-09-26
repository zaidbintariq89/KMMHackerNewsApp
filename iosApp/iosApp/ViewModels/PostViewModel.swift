//
//  PostViewModel.swift
//  iosApp
//
//  Created by Kaleem Asad on 11/09/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class PostViewModel: ObservableObject {
//    @Published var posts: [Post] = []
    @Published var rockets: [RocketLaunch] = []
    
    var spaceXSDK = NetworkRepo(databaseDriverFactory: DatabaseDriverFactory())

    
    func fetchData() async {
        
        spaceXSDK.getLaunches(forceReload: false) { rocketlist, err in
            self.rockets = rocketlist ?? []
        }
        
        
//        do {
//            if let url = URL(string: "https://jsonplaceholder.typicode.com/posts") {
//                let (data, _) = try await URLSession.shared.data(from: url)
//                let decoder = JSONDecoder()
//                let decodedPosts = try decoder.decode([Post].self, from: data)
//                DispatchQueue.main.async {
//                    self.posts = decodedPosts
//                }
//            }
//        } catch {
//            print("Error fetching data: \(error.localizedDescription)")
//        }
    }
}



