<c:import url="header.jsp"></c:import>

    <!-- breadcrumbs area start -->
    <div class="breadcrumbs_aree breadcrumbs_bg mb-110" data-bgimg="assets/img/others/breadcrumbs-bg.png">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="breadcrumbs_text">
                        <h1>Connexion | Inscription</h1>
                        <ul>
                            <li><a href="index.html">Accueil </a></li>
                            <li> // Connexion | Inscription</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- breadcrumbs area end -->
    <div class="login-register-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <form id="formConnexion" name="formConnexion" method="post" action="#">
                        <div class="login-form">
                            <h4 class="login-title">Connexion</h4>
                            <div class="row">
                                <div class="col-lg-12">
                                    <label>Adresse email*</label>
                                    <input type="email" placeholder="Adresse email">
                                </div>
                                <div class="col-lg-12">
                                    <label>Mot de passe</label>
                                    <input type="password" placeholder="Mot de passe">
                                </div>
                                <!-- <div class="col-sm-8 align-self-center">
                                    <div class="check-box">
                                        <input type="checkbox" id="remember_me">
                                        <label for="remember_me">Remember me</label>
                                    </div>
                                </div>
                                <div class="col-sm-4 pt-1 mt-md-0">
                                    <div class="forgotton-password_info">
                                        <a href="#"> Forgotten pasward?</a>
                                    </div>
                                </div> -->
                                <div class="col-lg-12 pt-5">
                                    <button class="btn custom-btn md-size">Conexion</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-6 pt-5 pt-lg-0">
                    <form id="formInscription" name="formInscription" method="post" action="#">
                        <div class="login-form">
                            <h4 class="login-title">Inscription</h4>
                            <div class="row">
                                <div class="col-md-6 col-12">
                                    <label>Nom</label>
                                    <input type="text" placeholder="Nom">
                                </div>
                                <div class="col-md-6 col-12">
                                    <label>Prénom</label>
                                    <input type="text" placeholder="Prénom">
                                </div>
                                <div class="col-md-12">
                                    <label>Adresse email*</label>
                                    <input type="email" placeholder="Adresse email">
                                </div>
                                <div class="col-md-6">
                                    <label>Mot de passe</label>
                                    <input type="password" placeholder="Mot de passe">
                                </div>
                                <div class="col-md-6">
                                    <label>Confirmer le mot de passe</label>
                                    <input type="password" placeholder="Confirmer le mot de passe">
                                </div>
                                <div class="col-12">
                                    <button class="btn custom-btn md-size">Inscription</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


<c:import url="footer.jsp"></c:import>