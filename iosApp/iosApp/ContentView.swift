
import SwiftUI
import shared

struct ContentView: View {
    @State private var username: String = ""
    @State private var password: String = ""
    @State private var isLoggedin: Bool = false

    var body: some View {
        NavigationView {
            VStack {
                Image(systemName: "person.circle")
                    .resizable()
                    .frame(width: 100, height: 100)
                    .padding()

                TextField("Username", text: $username)
                    .padding()
                    .background(Color(UIColor.systemGray6))
                    .cornerRadius(10)
                    .padding(.horizontal)

                SecureField("Password", text: $password)
                    .padding()
                    .background(Color(UIColor.systemGray6))
                    .cornerRadius(10)
                    .padding(.horizontal)

                Button(action: {
                    // Perform login logic here
                    // For this example, let's just toggle the login state
                    isLoggedin.toggle()
                }) {
                    Text("Log In")
                        .foregroundColor(.white)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.blue)
                        .cornerRadius(10)
                        .padding(.horizontal)
                }
                .padding(.top)

                Spacer()
            }
            .padding()
            .navigationTitle("Login")
            .navigationBarHidden(true)
        }
        .fullScreenCover(isPresented: $isLoggedin, content: {
            // Replace this with the main content of your app after successful login
//            Text("Logged in!")
            MainTabbedView()

        })
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
