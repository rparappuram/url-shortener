export function FeatureCard({ icon, title, description }: { icon: React.ReactNode; title: string; description: string }) {
    return (
      <div className="bg-white p-6 rounded-lg shadow-md text-center">
        <div className="mb-4 flex justify-center">{icon}</div>
        <h4 className="text-xl font-semibold mb-2">{title}</h4>
        <p className="text-gray-600">{description}</p>
      </div>
    );
  }