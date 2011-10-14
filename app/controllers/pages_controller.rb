class PagesController < ApplicationController
  def home
    @title = "Home"
  end

  def project
    @title = "Projects"
  end

  def resume
    @title = "Resume"
  end

  def contact
    @title = "Contact"
  end

end
