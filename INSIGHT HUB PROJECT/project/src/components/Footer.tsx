import React from 'react';
import { Mail, Phone, MapPin } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white">
      <div className="max-w-7xl mx-auto px-4 py-12 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div>
            <h3 className="text-lg font-semibold mb-4">Gates Institute</h3>
            <p className="text-gray-400">
              Empowering students with flexible learning solutions and comprehensive educational resources.
            </p>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Quick Links</h3>
            <ul className="space-y-2 text-gray-400">
              <li>About Us</li>
              <li>Courses</li>
              <li>Resources</li>
              <li>Contact</li>
            </ul>
          </div>
          <div>
            <h3 className="text-lg font-semibold mb-4">Contact Info</h3>
            <div className="space-y-2 text-gray-400">
              <p className="flex items-center"><Mail className="h-5 w-5 mr-2" /> info@gatesinstitute.com</p>
              <p className="flex items-center"><Phone className="h-5 w-5 mr-2" /> +1 (555) 123-4567</p>
              <p className="flex items-center"><MapPin className="h-5 w-5 mr-2" /> 123 Education Street, Learning City</p>
            </div>
          </div>
        </div>
        <div className="mt-8 pt-8 border-t border-gray-800 text-center text-gray-400">
          <p>&copy; {new Date().getFullYear()} Gates Institute. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;