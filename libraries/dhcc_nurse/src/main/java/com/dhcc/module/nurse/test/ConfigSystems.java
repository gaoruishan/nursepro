package com.dhcc.module.nurse.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("ConfigSystems")
public class ConfigSystems {
    @XStreamAsAttribute()
    @XStreamAlias("Group")
    public Group group = new Group();

    public Group getSystemGroup() {
        return group;
    }

    public void setCoordinateSystemGroup(Group stemGroup) {
        group = stemGroup;
    }

    @XStreamAlias("Group")
    public static class Group {
        @XStreamAsAttribute()
        @XStreamAlias("Name")
        public String Name="beijing54";
        @XStreamAlias("System")
        public System System =new System();

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public ConfigSystems.System getSystem() {
            return System;
        }

        public void setCoordinateSystem(ConfigSystems.System system) {
            System = system;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "Name='" + Name + '\'' +
                    ", System=" + System +
                    '}';
        }
    }
    @XStreamAlias("System")
    public static class System {
        @XStreamAsAttribute()
        @XStreamAlias("Name")
        public String Name="";
        @XStreamAlias("Ellipsoid")
        public Ellipsoid Ellipsoid = new Ellipsoid();
        @XStreamAlias("Projection")
        public Projection Projection= new Projection();

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public ConfigSystems.Ellipsoid getEllipsoid() {
            return Ellipsoid;
        }

        public void setEllipsoid(ConfigSystems.Ellipsoid ellipsoid) {
            Ellipsoid = ellipsoid;
        }

        public ConfigSystems.Projection getProjection() {
            return Projection;
        }

        public void setProjection(ConfigSystems.Projection projection) {
            Projection = projection;
        }

        @Override
        public String toString() {
            return "System{" +
                    "Name='" + Name + '\'' +
                    ", Ellipsoid=" + Ellipsoid +
                    ", Projection=" + Projection +
                    '}';
        }
    }
    public static class Ellipsoid {
        @XStreamAsAttribute()
        @XStreamAlias("Name")
        public String Name ="bj54";
        @XStreamAsAttribute()
        @XStreamAlias("a")
        public double a = 6378245.0;
        @XStreamAsAttribute()
        @XStreamAlias("f")
        public double f = 298.3;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public double getA() {
            return a;
        }

        public void setA(double a) {
            this.a = a;
        }

        public double getF() {
            return f;
        }

        public void setF(double f) {
            this.f = f;
        }

        @Override
        public String toString() {
            return "Ellipsoid{" +
                    "Name='" + Name + '\'' +
                    ", a=" + a +
                    ", f=" + f +
                    '}';
        }
    }
    public static class Projection {
        @XStreamAsAttribute()
        @XStreamAlias("AxisDirection")
        public int AxisDirection;
        @XStreamAsAttribute()
        @XStreamAlias("CuttingRadius")
        public double CuttingRadius;
        @XStreamAsAttribute()
        @XStreamAlias("FalseEast")
        public double FalseEast = 500000;

        public int getAxisDirection() {
            return AxisDirection;
        }

        public void setAxisDirection(int axisDirection) {
            AxisDirection = axisDirection;
        }

        public double getCuttingRadius() {
            return CuttingRadius;
        }

        public void setCuttingRadius(double cuttingRadius) {
            CuttingRadius = cuttingRadius;
        }

        public double getFalseEast() {
            return FalseEast;
        }

        public void setFalseEast(double falseEast) {
            FalseEast = falseEast;
        }

        @Override
        public String toString() {
            return "Projection{" +
                    "AxisDirection=" + AxisDirection +
                    ", CuttingRadius=" + CuttingRadius +
                    ", FalseEast=" + FalseEast +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ConfigSystems{" +
                "group=" + group +
                '}';
    }
}
