import SwiftUI
import shared

struct MainTabbedView: View {
    
    @StateObject private var currentReactives = SetReactiveMethods()
    @State var tabSelected = 0
    
    init(){
        UITabBar.appearance().backgroundColor = .white
    }
    
    let sdk = NetworkRepo(databaseDriverFactory: DatabaseDriverFactory())
    
    var body: some View {
        ZStack {
            TabView(selection: $tabSelected) {
                
                ServicesView()
                    .tabItem {
                        Image(systemName: "iphone")
                        Text("Services") // Add text to the tab
                    }
                    .tag(0)
                
                BillingView(viewModel: .init(sdk: sdk))
                    .tabItem {
                        Image(systemName: "dollarsign.square")
                        Text("Billing") // Add text to the tab
                    }
                    .tag(1)
                
                MessagesView()
                    .tabItem {
                        Image(systemName: "message.badge.filled.fill")
                        Text("Messages") // Add text to the tab
                    }
                    .tag(2)
                
                SupportView()
                    .tabItem {
                        Image(systemName: "message")
                        Text("Support") // Add text to the tab
                    }
                    .tag(3)
                
                MoreView()
                    .tabItem {
                        Image(systemName: "ellipsis")
                        Text("More") // Add text to the tab
                    }
                    .tag(4)
                
            }
            .accentColor(.pink)
            
            FullScreenNavigationView(isShowing: $currentReactives.presentFullScreenView, content: AnyView(NavigationView { currentReactives.viewToShow.view }))
            
        }
    }
}

struct MainTabbedView_Previews: PreviewProvider {
    static var previews: some View {
        MainTabbedView()
    }
}
