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
                buttons: getActionSheetButtons()
            )
        }
    }
    func getActionSheetButtons() -> [ActionSheet.Button] {
        var buttons: [ActionSheet.Button] = []
        
        let phoneNumbers = ["(416) 301-0069", "(413) 301-0069"]
        
        for phoneNumber in phoneNumbers {
            let button = ActionSheet.Button.default(Text("Demo: \(phoneNumber)")) {
                // Action to perform when the button is tapped
                // You can add your code here
            }
            buttons.append(button)
        }
        
        // Add a cancel button
        let cancelButton = ActionSheet.Button.cancel()
        buttons.append(cancelButton)
        
        return buttons
    }
    var body: some View {
        VStack(spacing: 10){
//            Spacer()
            accountSelectView()
            Spacer()
//            TabBarView(currentTab: self.$currentTab)
//            TabView(selection: self.$currentTab) {
//                UsageView(viewModel: UsageView.ViewModel(sdk: sdk)).tag(0)
//                EquipmentView().tag(1)
//                PackageView().tag(2)
//            }
//            .tabViewStyle(.page(indexDisplayMode: .never))
            //                .edgesIgnoringSafeArea(.all)
        }
        .background(Color.gray.opacity(0.2)) // Set the background color to yellow for the entire body
    }
}

struct BillingView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

