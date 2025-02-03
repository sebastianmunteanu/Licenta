const BASE_URL = "http://192.168.1.170:8080/";
//  window.location.hostname === "localhost" ? "http://192.168.1.170:8080/" : "http://localhost:8080/";

export const fetchWithAuth = async (endpoint, options = {}) => {
  const token = sessionStorage.getItem("token");

  const headers = {
    ...options.headers,
    Authorization: `Bearer ${token}`
  };

  try {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      ...options,
      headers
    });

    if (response.status === 403) {
      console.log("403 ERROR");
      sessionStorage.removeItem("token");
      if (window.location.pathname !== "/authentication/login") {
        window.location.href = "/authentication/login";
      }
      return;
    }

    return response;
  } catch (error) {
    console.log("EROARE CONECTARE BACKEND");
    return new Response(JSON.stringify({ message: "Server connection error" }), {
      status: 503,
      statusText: "Server Error"
    });
    // console.error("Conexiunea cu serverul a eșuat:", error);
    // await sleep(10000);
    // sessionStorage.removeItem("token");
    // if (window.location.pathname !== "/authentication/login") {
    //   window.location.href = "/authentication/login";
    // }
  }
};
