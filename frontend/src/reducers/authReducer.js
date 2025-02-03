export const initialState = {
  user: null,
  isAuthenticated: false
};

export function authReducer(state, action) {
  switch (action.type) {
    case "login":
      return {
        ...state,
        user: action.payload,
        isAuthenticated: true
      };
    case "logout":
      return { ...state, user: null, isAuthenticated: false };
    default:
      throw new Error("not a valid case");
  }
}
