import {Outlet} from "react-router-dom";
import {Avatar, SideNav, SideNavItem} from "@vaadin/react-components";

export default function MainLayout() {
    return (
        <div className="flex flex-col w-full h-full">
            <header className="flex flex-row justify-between px-m box-border" {...{theme: "dark"}}>
                <div className="flex gap-l items-center overflow-hidden">
                    <img src="images/vaadin.png" style={{height: "44px"}} alt="Application logo"/>
                    <SideNav>
                        <SideNavItem className="inline-block h-full" path="hilla/employees">Employees</SideNavItem>
                        <SideNavItem className="inline-block h-full" path="hilla/teams">Teams</SideNavItem>
                        <SideNavItem className="inline-block h-full" path="hilla/locations">Locations</SideNavItem>
                    </SideNav>
                </div>
                <div className="flex gap-s items-center">
                    <Avatar colorIndex={1} name="FL" img="images/avatars/user.jpg"/>
                </div>
            </header>
            <div className="min-h-0 flex-grow overflow-auto">
                <Outlet/>
            </div>
        </div>
    )
}