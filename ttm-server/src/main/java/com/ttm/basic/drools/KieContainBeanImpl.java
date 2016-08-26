package com.ttm.basic.drools;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.runtime.*;

import java.util.Collection;

/**
 * Created by liguoqing on 2016/6/28.
 */
public class KieContainBeanImpl implements KieContainBean{

    private KieContainer kieContainer;
    private KieServices kieServices;

    public KieContainBeanImpl(KieServices kieServices){
          this.kieServices = kieServices;
          this.kieContainer = this.kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
    }

    @Override
    public ReleaseId getReleaseId() {
        return null;
    }

    @Override
    public Results verify() {
        return null;
    }

    @Override
    public Results verify(String... strings) {
        return null;
    }

    @Override
    public Results updateToVersion(ReleaseId releaseId) {
        return null;
    }

    @Override
    public Collection<String> getKieBaseNames() {
        return null;
    }

    @Override
    public Collection<String> getKieSessionNamesInKieBase(String s) {
        return null;
    }

    @Override
    public KieBase getKieBase() {
        return null;
    }

    @Override
    public KieBase getKieBase(String s) {
        return null;
    }

    @Override
    public KieBase newKieBase(KieBaseConfiguration kieBaseConfiguration) {
        return null;
    }

    @Override
    public KieBase newKieBase(String s, KieBaseConfiguration kieBaseConfiguration) {
        return null;
    }

    @Override
    public KieSession newKieSession() {
        return null;
    }

    @Override
    public KieSession newKieSession(KieSessionConfiguration kieSessionConfiguration) {
        return null;
    }

    @Override
    public KieSession newKieSession(Environment environment) {
        return null;
    }

    @Override
    public KieSession newKieSession(Environment environment, KieSessionConfiguration kieSessionConfiguration) {
        return null;
    }

    @Override
    public KieSession newKieSession(String s) {
        return null;
    }

    @Override
    public KieSession newKieSession(String s, Environment environment) {
        return null;
    }

    @Override
    public KieSession newKieSession(String s, KieSessionConfiguration kieSessionConfiguration) {
        return null;
    }

    @Override
    public KieSession newKieSession(String s, Environment environment, KieSessionConfiguration kieSessionConfiguration) {
        return null;
    }

    @Override
    public StatelessKieSession newStatelessKieSession() {
        return null;
    }

    @Override
    public StatelessKieSession newStatelessKieSession(KieSessionConfiguration kieSessionConfiguration) {
        return null;
    }

    @Override
    public StatelessKieSession newStatelessKieSession(String s) {
        return null;
    }

    @Override
    public StatelessKieSession newStatelessKieSession(String s, KieSessionConfiguration kieSessionConfiguration) {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public KieSessionConfiguration getKieSessionConfiguration() {
        return null;
    }

    @Override
    public KieSessionConfiguration getKieSessionConfiguration(String s) {
        return null;
    }

    @Override
    public KieBaseModel getKieBaseModel(String s) {
        return null;
    }

    @Override
    public KieSessionModel getKieSessionModel(String s) {
        return null;
    }
}
