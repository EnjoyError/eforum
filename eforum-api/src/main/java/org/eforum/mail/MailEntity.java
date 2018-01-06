package org.eforum.mail;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailEntity {
    /**
     * 收件人
     */
    private ArrayList<String> tos;
    /**
     * 抄送
     */
    private ArrayList<String> ccs;
    /**
     * 密送
     */
    private ArrayList<String> bccs;

    /**
     * 邮件主题
     */
    private String subject;


    /**
     * 邮件正文文本（一些关键信息） 经过format后变成标准的正文文本
     */
    private String text;

    /**
     * 邮件正文文本map 对应模板的key
     */
    private Map<String,Object> values;
    /**
     * 发件时间
     */
    private Date sendDate;

    /**
     * 附件
     */
    private ArrayList<String> files;

    public MailEntity(){
        this.tos = new ArrayList<>(1);
        this.ccs = new ArrayList<>(1);
        this.bccs = new ArrayList<>(1);
        this.files = new ArrayList<>(1);
        this.values = new HashMap<>();
        this.subject = null;
        this.sendDate = new Date();
        this.text = null;
    }

    /**
     * @param to 收件人
     * @param subject 邮件主题
     */
    public MailEntity(String to, String subject) {
        this.tos = new ArrayList<>(1);
        this.ccs = new ArrayList<>(1);
        this.bccs = new ArrayList<>(1);
        this.files = new ArrayList<>(1);
        this.values = new HashMap<>();
        this.tos.add(to);
        this.subject = subject;
        this.text = text;
    }

    /**
     * 添加附件
     * @param file 添加附件
     * @return
     */
    public boolean addFile(String file){
        return this.files.add(file);
    }

    /**
     * 添加收件人
     * @param to 收件人
     * @return
     */
    public boolean addTo(String to){
        return this.tos.add(to);
    }

    /**
     * 添加抄送人
     * @param cc 抄送人
     * @return
     */
    public boolean addCC(String cc){
        return this.ccs.add(cc);
    }

    /**
     * 添加密送人
     * @param bcc 密送人
     * @return
     */
    public boolean addBcc(String bcc){
        return this.bccs.add(bcc);
    }

    public boolean addValue(String key,Object value){
        return this.values.put(key,value)!=null;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }
    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public ArrayList<String> getTos() {
        return tos;
    }

    public void setTos(ArrayList<String> tos) {
        this.tos = tos;
    }

    public ArrayList<String> getCcs() {
        return ccs;
    }

    public void setCcs(ArrayList<String> ccs) {
        this.ccs = ccs;
    }

    public ArrayList<String> getBccs() {
        return bccs;
    }

    public void setBccs(ArrayList<String> bccs) {
        this.bccs = bccs;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
