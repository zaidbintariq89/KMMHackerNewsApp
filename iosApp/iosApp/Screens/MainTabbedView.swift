import SwiftUI
import shared

// MainTabbedView is the main SwiftUI view for your tabbed interface.
struct MainTabbedView: View {
    
    // State object to manage reactive properties
    @StateObject private var currentReactives = SetReactiveMethods()
    
    // State variable to keep track of the selected tab
    @State var tabSelected = 0
    
    // Initialize the view
    init(){
        // Set the appearance of the UITabBar
        UITabBar.appearance().backgroundColor = .white
    }
    
    // Create an instance of NetworkRepo for data fetching
    let sdk = NetworkRepo(databaseDriverFactory: DatabaseDriverFactory())
    
    // Define the top header view
    fileprivate func topHeaderView() -> some View {
        return ZStack {
            AppColors.appRed
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                HStack {
                    Spacer(minLength: 20)
                    
                    // Welcome text with styling
                    Text("Welcome, User!")
                        .font(.title2)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                    Spacer()
                    
                    // Profile image icon
                    Image(systemName: "person.circle")
                        .resizable()
                        .font(.largeTitle)
                        .foregroundColor(.white)
                        .frame(width: 20, height: 20) // Fixed width and height
                        .padding(20)
                }
                
            }
            .frame(height: 90)
        }
        .frame(height: 30)
    }
    
    var body: some View {
        VStack {
            
            // Include the top header view in the main layout
            topHeaderView()
            
            ZStack {
                TabView(selection: $tabSelected) {
                    
                    // Tab 1: ServicesView
                    ServicesView(viewModel: .init(sdk: sdk))
                        .tabItem {
                            Image(systemName: "iphone")
                            Text("Services") // Add text to the tab
                        }
                        .tag(0)
                    
                    // Tab 2: BillingView
                    BillingView()
                        .tabItem {
                            Image(systemName: "dollarsign.square")
                            Text("Billing") // Add text to the tab
                        }
                        .tag(1)
                    
                    // Tab 3: MessagesView
                    MessagesView(viewModel: .init(sdk: sdk))
                        .tabItem {
                            Image(systemName: "message.badge.filled.fill")
                            Text("Messages") // Add text to the tab
                        }
                        .tag(2)
                    
                    // Tab 4: SupportView
                    SupportView()
                        .tabItem {
                            Image(systemName: "message")
                            Text("Support") // Add text to the tab
                        }
                        .tag(3)
                    
                    // Tab 5: MoreView
                    MoreView()
                        .tabItem {
                            Image(systemName: "ellipsis")
                            Text("More") // Add text to the tab
                        }
                        .tag(4)
                    
                }
                .accentColor(.pink)
                
                // Full-screen navigation view
                FullScreenNavigationView(isShowing: $currentReactives.presentFullScreenView, content: AnyView(NavigationView { currentReactives.viewToShow.view }))
                
            }
        }
    }
}

// Preview provider for MainTabbedView
struct MainTabbedView_Previews: PreviewProvider {
    static var previews: some View {
        MainTabbedView()
    }
}
