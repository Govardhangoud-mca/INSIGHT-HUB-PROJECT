import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { GraduationCap, Menu, X } from 'lucide-react';

const Navbar = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [activeLink, setActiveLink] = useState(''); // Track the active link

  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  const handleLinkClick = (path: string) => {
    setActiveLink(path); // Set the active link when clicked
    setIsMobileMenuOpen(false); // Close mobile menu on link click
  };

  return (
    <nav className="bg-blue-900 text-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-20">
          {/* Logo and Brand Name */}
          <Link to="/" className="flex items-center space-x-2" onClick={() => handleLinkClick('/')}>
            <GraduationCap className="h-10 w-10 text-white hover:text-orange-100 transition-colors" />
            <span className="text-2xl font-bold hover:text-orange-100 transition-colors">
              Gates Institute
            </span>
          </Link>

          {/* Desktop Menu */}
          <div className="hidden md:flex items-center space-x-6">
            <Link 
              to="/" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/')}
            >
              Home
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/about" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/about' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/about')}
            >
              About
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/about' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/teachers" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/teachers' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/teachers')}
            >
              Teachers
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/teachers' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/recorded-lectures" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/recorded-lectures' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/recorded-lectures')}
            >
              Lectures
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/recorded-lectures' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/study-materials" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/study-materials' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/study-materials')}
            >
              Materials
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/study-materials' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/live-sessions" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/live-sessions' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/live-sessions')}
            >
              Live Sessions
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/live-sessions' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/signin" 
              className={`px-3 py-2 transition-colors relative group ${
                activeLink === '/signin' ? 'text-orange-100' : 'hover:text-orange-100'
              }`}
              onClick={() => handleLinkClick('/signin')}
            >
              Sign In
              <span className={`absolute bottom-0 left-0 h-0.5 bg-orange-100 transition-all ${
                activeLink === '/signin' ? 'w-full' : 'w-0 group-hover:w-full'
              }`}></span>
            </Link>
            <Link 
              to="/signup" 
              className="bg-white text-orange-500 px-6 py-2 rounded-md font-medium hover:bg-orange-100 hover:text-orange-600 transition-colors"
              onClick={() => handleLinkClick('/signup')}
            >
              Sign Up
            </Link>
          </div>

          {/* Mobile Menu Toggle Button */}
          <div className="md:hidden">
            <button 
              onClick={toggleMobileMenu}
              className="p-2 focus:outline-none"
            >
              {isMobileMenuOpen ? (
                <X className="h-6 w-6 text-white hover:text-orange-100 transition-colors" />
              ) : (
                <Menu className="h-6 w-6 text-white hover:text-orange-100 transition-colors" />
              )}
            </button>
          </div>
        </div>

        {/* Mobile Menu */}
        {isMobileMenuOpen && (
          <div className="md:hidden bg-orange-500">
            <div className="flex flex-col space-y-4 px-4 pb-4">
              <Link 
                to="/" 
                className={`py-2 transition-colors ${
                  activeLink === '/' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/')}
              >
                Home
              </Link>
              <Link 
                to="/about" 
                className={`py-2 transition-colors ${
                  activeLink === '/about' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/about')}
              >
                About
              </Link>
              <Link 
                to="/teachers" 
                className={`py-2 transition-colors ${
                  activeLink === '/teachers' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/teachers')}
              >
                Teachers
              </Link>
              <Link 
                to="/recorded-lectures" 
                className={`py-2 transition-colors ${
                  activeLink === '/recorded-lectures' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/recorded-lectures')}
              >
                Lectures
              </Link>
              <Link 
                to="/study-materials" 
                className={`py-2 transition-colors ${
                  activeLink === '/study-materials' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/study-materials')}
              >
                Materials
              </Link>
              <Link 
                to="/live-sessions" 
                className={`py-2 transition-colors ${
                  activeLink === '/live-sessions' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/live-sessions')}
              >
                Live Sessions
              </Link>
              <Link 
                to="/signin" 
                className={`py-2 transition-colors ${
                  activeLink === '/signin' ? 'text-orange-100' : 'hover:text-orange-100'
                }`}
                onClick={() => handleLinkClick('/signin')}
              >
                Sign In
              </Link>
              <Link 
                to="/signup" 
                className="bg-white text-orange-500 px-6 py-2 rounded-md font-medium hover:bg-orange-100 hover:text-orange-600 transition-colors text-center"
                onClick={() => handleLinkClick('/signup')}
              >
                Sign Up
              </Link>
            </div>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;