import App from "App";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

// Material Dashboard 2 React Context Provider
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { MaterialUIControllerProvider } from "context";
import { AuthProvider } from "context/AuthContext";
import { SensorProvider } from "context/SensorContext";

const container = document.getElementById("app");
const root = createRoot(container);
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 0
    }
  }
});

root.render(
  <BrowserRouter>
    <MaterialUIControllerProvider>
      <AuthProvider>
        <QueryClientProvider client={queryClient}>
          <SensorProvider>
            <App />
          </SensorProvider>
        </QueryClientProvider>
      </AuthProvider>
    </MaterialUIControllerProvider>
  </BrowserRouter>
);
