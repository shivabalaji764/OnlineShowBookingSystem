import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/UserLogin.css";

export default function UserLogin() {
  const navigate = useNavigate();
  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async function (e) {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const response = await fetch("http://localhost:5431/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify({ userEmail, password }),
      });

      if (response.ok) {
        navigate("/home");
      } else {
        setError("Invalid email or password. Please try again.");
      }
    } catch (err) {
      setError(err+" Something went wrong. Please check your connection.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">

      <div className="login-left">
        <div className="login-left-orb login-left-orb-1" />
        <div className="login-left-orb login-left-orb-2" />
        <div className="login-left-orb login-left-orb-3" />

        <div className="login-bubbles">
          <div className="bubble" />
          <div className="bubble" />
          <div className="bubble" />
          <div className="bubble" />
          <div className="bubble" />
        </div>

        <div className="login-left-content">
          <div className="login-logo">
            <div className="login-logo-icon">✦</div>
            <span className="login-logo-name">YourApp</span>
          </div>

          <h1 className="login-hero-title">
            Everything you need,<br />
            <span>beautifully simple.</span>
          </h1>
          <p className="login-hero-sub">
            Sign in and get back to what matters most. Fast, secure, and designed for you.
          </p>

          <div className="login-features">
            <div className="login-feature">
              <div className="login-feature-icon">🔒</div>
              <span className="login-feature-text">End-to-end encrypted sessions</span>
            </div>
            <div className="login-feature">
              <div className="login-feature-icon">⚡</div>
              <span className="login-feature-text">Instant access across all devices</span>
            </div>
            <div className="login-feature">
              <div className="login-feature-icon">🎯</div>
              <span className="login-feature-text">Personalized dashboard on login</span>
            </div>
          </div>
        </div>
      </div>

      <div className="login-right">
        <div className="login-card">

          <div className="login-card-header">
            <p className="login-card-eyebrow">Welcome back</p>
            <h2 className="login-card-title">Sign in</h2>
            <p className="login-card-sub">Enter your credentials to continue.</p>
          </div>

          {error && <div className="login-error">{error}</div>}

          <form className="login-form" onSubmit={handleSubmit}>
            <div className="login-field">
              <label className="login-label" htmlFor="email">Email</label>
              <input
                id="email"
                className="login-input"
                type="email"
                placeholder="you@example.com"
                value={userEmail}
                onChange={(e) => setUserEmail(e.target.value)}
                required
                autoComplete="email"
              />
            </div>

            <div className="login-field">
              <label className="login-label" htmlFor="password">Password</label>
              <input
                id="password"
                className="login-input"
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                autoComplete="current-password"
              />
            </div>

            <button className="login-btn-primary" type="submit" disabled={loading}>
              {loading && <span className="login-spinner" />}
              {loading ? "Signing in…" : "Sign In"}
            </button>
          </form>

          {/* Signup CTA */}
          <div className="login-divider">
            <span className="login-divider-line" />
            <span className="login-divider-text">New here?</span>
            <span className="login-divider-line" />
          </div>

          <div className="login-signup-cta">
            <p>Don't have an account yet?</p>
            <button
              className="login-btn-secondary"
              type="button"
              onClick={() => navigate("/signup")}
            >
              Create an account
            </button>
          </div>

        </div>
      </div>
    </div>
  );
}