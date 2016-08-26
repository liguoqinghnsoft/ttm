package com.ttm.basic.drools;

import org.drools.core.SessionConfiguration;
import org.kie.api.KieBase;
import org.kie.api.command.Command;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.*;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.runtime.rule.*;
import org.kie.api.time.SessionClock;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liguoqing on 2016/6/28.
 */
public class KieSessionBeanImpl implements KieSessionBean{

    private KieSession kieSession;

    public KieSessionBeanImpl(KieServiceBean kieServices, KieContainBean kieContainer) {
        this(kieServices, kieContainer, null);
    }

    public KieSessionBeanImpl(KieServiceBean kieServices, KieContainBean kieContainer, Properties droolsProperties) {
        KieSessionConfiguration conf;
        if (droolsProperties == null) {
            conf = SessionConfiguration.getDefaultInstance();
        } else {
            conf = SessionConfiguration.newInstance(droolsProperties);
        }
        this.kieSession = kieContainer.newKieSession(conf);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public long getIdentifier() {
        return 0;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public <T> T execute(Command<T> command) {
        return null;
    }

    @Override
    public <T extends SessionClock> T getSessionClock() {
        return null;
    }

    @Override
    public void setGlobal(String s, Object o) {

    }

    @Override
    public Object getGlobal(String s) {
        return null;
    }

    @Override
    public Globals getGlobals() {
        return null;
    }

    @Override
    public Calendars getCalendars() {
        return null;
    }

    @Override
    public Environment getEnvironment() {
        return null;
    }

    @Override
    public KieBase getKieBase() {
        return null;
    }

    @Override
    public void registerChannel(String s, Channel channel) {

    }

    @Override
    public void unregisterChannel(String s) {

    }

    @Override
    public Map<String, Channel> getChannels() {
        return null;
    }

    @Override
    public KieSessionConfiguration getSessionConfiguration() {
        return null;
    }

    @Override
    public void halt() {

    }

    @Override
    public Agenda getAgenda() {
        return null;
    }

    @Override
    public EntryPoint getEntryPoint(String s) {
        return null;
    }

    @Override
    public Collection<? extends EntryPoint> getEntryPoints() {
        return null;
    }

    @Override
    public QueryResults getQueryResults(String s, Object... objects) {
        return null;
    }

    @Override
    public LiveQuery openLiveQuery(String s, Object[] objects, ViewChangedEventListener viewChangedEventListener) {
        return null;
    }

    @Override
    public String getEntryPointId() {
        return null;
    }

    @Override
    public FactHandle insert(Object o) {
        return null;
    }

    @Override
    public void retract(FactHandle factHandle) {

    }

    @Override
    public void delete(FactHandle factHandle) {

    }

    @Override
    public void delete(FactHandle factHandle, FactHandle.State state) {

    }

    @Override
    public void update(FactHandle factHandle, Object o) {

    }

    @Override
    public FactHandle getFactHandle(Object o) {
        return null;
    }

    @Override
    public Object getObject(FactHandle factHandle) {
        return null;
    }

    @Override
    public Collection<? extends Object> getObjects() {
        return null;
    }

    @Override
    public Collection<? extends Object> getObjects(ObjectFilter objectFilter) {
        return null;
    }

    @Override
    public <T extends FactHandle> Collection<T> getFactHandles() {
        return null;
    }

    @Override
    public <T extends FactHandle> Collection<T> getFactHandles(ObjectFilter objectFilter) {
        return kieSession.getFactHandles(objectFilter);
    }

    @Override
    public long getFactCount() {
        return kieSession.getFactCount();
    }

    @Override
    public KieRuntimeLogger getLogger() {
        return kieSession.getLogger();
    }

    @Override
    public void addEventListener(ProcessEventListener processEventListener) {
         kieSession.addEventListener(processEventListener);
    }

    @Override
    public void removeEventListener(ProcessEventListener processEventListener) {
        kieSession.removeEventListener(processEventListener);
    }

    @Override
    public Collection<ProcessEventListener> getProcessEventListeners() {
        return kieSession.getProcessEventListeners();
    }

    @Override
    public ProcessInstance startProcess(String s) {
        return kieSession.startProcess(s);
    }

    @Override
    public ProcessInstance startProcess(String s, Map<String, Object> map) {
        return kieSession.startProcess(s,map);
    }

    @Override
    public ProcessInstance createProcessInstance(String s, Map<String, Object> map) {
        return kieSession.createProcessInstance(s,map);
    }

    @Override
    public ProcessInstance startProcessInstance(long l) {
        return kieSession.startProcessInstance(l);
    }

    @Override
    public void signalEvent(String s, Object o) {
        kieSession.signalEvent(s,o);
    }

    @Override
    public void signalEvent(String s, Object o, long l) {
        kieSession.signalEvent(s,o,l);
    }

    @Override
    public Collection<ProcessInstance> getProcessInstances() {
        return kieSession.getProcessInstances();
    }

    @Override
    public ProcessInstance getProcessInstance(long l) {
        return kieSession.getProcessInstance(l);
    }

    @Override
    public ProcessInstance getProcessInstance(long l, boolean b) {
        return kieSession.getProcessInstance(l,b);
    }

    @Override
    public void abortProcessInstance(long l) {
        kieSession.abortProcessInstance(l);
    }

    @Override
    public WorkItemManager getWorkItemManager() {
        return null;
    }

    @Override
    public void addEventListener(RuleRuntimeEventListener ruleRuntimeEventListener) {
        kieSession.addEventListener(ruleRuntimeEventListener);
    }

    @Override
    public void removeEventListener(RuleRuntimeEventListener ruleRuntimeEventListener) {
        kieSession.removeEventListener(ruleRuntimeEventListener);
    }

    @Override
    public Collection<RuleRuntimeEventListener> getRuleRuntimeEventListeners() {
        return kieSession.getRuleRuntimeEventListeners();
    }

    @Override
    public void addEventListener(AgendaEventListener agendaEventListener) {
       kieSession.addEventListener(agendaEventListener);
    }

    @Override
    public void removeEventListener(AgendaEventListener agendaEventListener) {
       kieSession.removeEventListener(agendaEventListener);
    }

    @Override
    public Collection<AgendaEventListener> getAgendaEventListeners() {
        return kieSession.getAgendaEventListeners();
    }

    @Override
    public int fireAllRules() {
        return 0;
    }

    @Override
    public int fireAllRules(int i) {
        return 0;
    }

    @Override
    public int fireAllRules(AgendaFilter agendaFilter) {
        return 0;
    }

    @Override
    public int fireAllRules(AgendaFilter agendaFilter, int i) {
        return 0;
    }

    @Override
    public void fireUntilHalt() {

    }

    @Override
    public void fireUntilHalt(AgendaFilter agendaFilter) {

    }
}
