package com.dhcc.module.nurse.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 */
@XStreamAlias("ConfigTest")
public class ConfigTest {
    public ConfigTest() {
        testOne = new TestOne();
        testTwo = new TestTwo();
        testThree = new TestThree();
        testFour = new TestFour();
    }

    @XStreamAlias("TestOne")
    public TestOne testOne;
    @XStreamAlias("TestTwo")
    public TestTwo testTwo;

    @XStreamAlias("TestThree")
    public TestThree testThree;
    @XStreamAlias("TestFour")
    public TestFour testFour;

    public static class TestOne {
        public int index;
        public double promptBound = 1;
        public boolean displayAll = true;

        @Override
        public String toString() {
            return "TestOne{" +
                    "index=" + index +
                    ", promptBound=" + promptBound +
                    ", displayAll=" + displayAll +
                    '}';
        }
    }

    public static class TestTwo {
        public int index;
        public double promptBound = 1;
        public boolean displayAll = true;

        @Override
        public String toString() {
            return "TestTwo{" +
                    "index=" + index +
                    ", promptBound=" + promptBound +
                    ", displayAll=" + displayAll +
                    '}';
        }
    }


    public static class TestThree {
        private int index;
        private double promptBound = 1;
        private boolean displayAll = true;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getPromptBound() {
            return promptBound;
        }

        public void setPromptBound(double promptBound) {
            this.promptBound = promptBound;
        }

        public boolean isDisplayAll() {
            return displayAll;
        }

        public void setDisplayAll(boolean displayAll) {
            this.displayAll = displayAll;
        }

        @Override
        public String toString() {
            return "TestThree{" +
                    "index=" + index +
                    ", promptBound=" + promptBound +
                    ", displayAll=" + displayAll +
                    '}';
        }
    }

    public static class TestFour {
        private int index;
        private double promptBound = 1;
        private boolean displayAll = true;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getPromptBound() {
            return promptBound;
        }

        public void setPromptBound(double promptBound) {
            this.promptBound = promptBound;
        }

        public boolean isDisplayAll() {
            return displayAll;
        }

        public void setDisplayAll(boolean displayAll) {
            this.displayAll = displayAll;
        }

        @Override
        public String toString() {
            return "TestFour{" +
                    "index=" + index +
                    ", promptBound=" + promptBound +
                    ", displayAll=" + displayAll +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ConfigTest{" +
                "testOne=" + testOne +
                ", testTwo=" + testTwo +
                ", testThree=" + testThree +
                ", testFour=" + testFour +
                '}';
    }
}
