//
//  SearchView.swift
//  FullScreenNavigationView
//
//

import SwiftUI

//struct SearchView: View {
//    @StateObject var viewModel = PostViewModel()
//
//    var body: some View {
//        NavigationView {
//            if #available(iOS 15.0, *) {
//                List(viewModel.posts) { post in
//                    VStack(alignment: .leading) {
//                        Text(post.title)
//                            .font(.headline)
//                        Text(post.body)
//                            .font(.subheadline)
//                    }
//                }
//                .navigationTitle("Posts")
//                .task {
//                    await viewModel.fetchData()
//                }
//            } else {
//                // Fallback on earlier versions
//            }
//        }
//    }
//}

struct BillingView: View {
    var body: some View {
        Text("Sessions View!")
    }
}
