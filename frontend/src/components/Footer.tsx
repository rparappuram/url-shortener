export function Footer() {
    return (
      <footer className="border-t">
        <div className="container mx-auto px-4 py-6 text-center text-sm text-gray-600">
          © 2025 {import.meta.env.VITE_APP_NAME}. All rights reserved.
        </div>
      </footer>
    );
  }