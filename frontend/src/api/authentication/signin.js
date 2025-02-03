export const addNewUser = async (name, email, password) => {
  try {
    const response = await fetch("http://192.168.1.170:8080/register-user", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ name, email, password })
    });
    return response;
  } catch {
    console.log("error");
  }
};
