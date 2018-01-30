<%@ include file="/notification-viewer-init.jsp" %>

<nav id="nav-side">

        <div id="nav-notifications">
            <button id="trigger-notifications">
                <div class="notif-flexbox">
                    <div class="notif-picto">
                        <div class="notif-amount"><c:out value="${notifCount}"/></div>
                    </div>
                    <!-- #dev Si notifications.count != 0 --> <div class="notification-text">Notifications</div>
                </div>
            </button>
            <div class="notif-list">
                <a href="#" class="notif-item">
                    <div class="notif-date">10.01</div>
                    <div class="notif-text" data-dot="2">Nouveau document disponible dans votre espace personnel</div>
                </a>
                <a href="#" class="notif-item">
                    <div class="notif-date">10.01</div>
                    <div class="notif-text" data-dot="2">Nouveau document disponible dans votre espace personnel</div>
                </a>
                <!-- #dev Si notif non lue => class .new -->
                <a href="#" class="notif-item new">
                    <div class="notif-date">10.01</div>
                    <div class="notif-text" data-dot="2">Nouveau document disponible dans votre espace personnel et encore plus de text pour dot</div>
                </a>
                <div class="notif-last">
                    <a href="#" class="btn-square--filled--core"><span class="flexbox"><span class="btn-text">Voir tous</span><span class="btn-arrow"></span></span></a>
                </div>
            </div>
        </div>

        <div id="nav-menu">
            <button id="trigger-menu">
                <div class="menu-picto"></div>
            </button>
            <div class="menu-list">
                <a href="#" class="nav-item account">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Mon compte</div>
                    </span>
                </a>
                <a href="#" class="nav-item favorite">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Mes favoris</div>
                    </span>
                </a>
                <a href="#" class="nav-item demarches">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Mes dÃ©marches</div>
                    </span>
                </a>
                <a href="#" class="nav-item bills">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Mes paiements</div>
                    </span>
                </a>
                <a href="#" class="nav-item folder">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Mes portedocuments</div>
                    </span>
                </a>
                <a href="#" class="nav-item proximity">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Autour de moi</div>
                    </span>
                </a>
                <a href="#" class="nav-item tour">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Visite guidÃ©es</div>
                    </span>
                </a>
                <div class="fill"></div>
                <a href="#" class="nav-item custom">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Personnaliser</div>
                    </span>
                </a>
                <button class="nav-item logout">
                    <span class="flexbox">
                        <div class="item-picto"></div>
                        <div class="item-text">Me dÃ©connecter</div>
                    </span>
                </button>
            </div>
        </div>

    </nav>