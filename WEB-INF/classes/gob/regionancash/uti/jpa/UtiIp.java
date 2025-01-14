package gob.regionancash.uti.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "uti_ip")
public class UtiIp
        implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 145)
    private String category;
    @Size(max = 145)
    private String code;
    @Size(max = 145)
    private String name;
    @Column(name = "people_id")
    private Integer peopleId;
    @Column(name = "dependency_id")
    private Integer dependencyId;
    @Size(max = 145)
    private String position;
    private boolean active;
    @Size(max = 145)
    private String antivirus;
    @Size(max = 145)
    private String restriction;
    @Column(name = "remote_server")
    private String remoteServer;
    @Column(name = "remote_pass")
    private String remotePass;
    @Column(name = "remote_code")
    private String remoteCode;
    @Size(max = 145)
    private String description;
    @Size(max = 145)
    @Column(name = "mac_id")
    private String macId;
    @Size(max = 145)
    private String workgroup;
    @Size(max = 145)
    private String device;
    @Size(max = 145)
    @Column(name = "patrimonial_code")
    private String patrimonialCode;
    @Column(name = "creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    private boolean stabilizer;
    @Size(max = 145)
    @Column(name = "stabilizer_code")
    private String stabilizerCode;
    @Size(max = 145)
    @Column(name = "stabilizer_description")
    private String stabilizerDescription;
    private boolean printer;
    @Size(max = 145)
    @Column(name = "printer_code")
    private String printerCode;
    @Size(max = 145)
    @Column(name = "printer_description")
    private String printerDescription;
    @Size(max = 145)
    @Column(name = "printer_serie")
    private String printerSerie;
    @Size(max = 145)
    @Column(name = "printer_model")
    private String printerModel;
    private boolean canceled;
    private Integer user;
    @Column(name = "type")
    private Character type;
    private String siaf;
    @Column(name = "user_siaf")
    private String userSiaf;
    @Column(name = "clave")
    private String clave;
    private Integer n3;
    private Integer n4;
    @Size(max = 100)
    @Column(name = "device_serie")
    private String deviceSerie;
    @Size(max = 100)
    @Column(name = "device_model")
    private String deviceModel;
    @Column(name = "maintenance_type")
    private Character maintenanceType;
    @Column(name = "maintenance_date")
    private Date maintenanceDate;


    @Size(max = 100)
    @Column(name = "license_serie")
    private String licenseSerie;
    @Size(max = 100)
    @Column(name = "activation_code")
    private String activationCode;
    @Column(name = "activation_date")
    private Date activationDate;

    public String getLicenseSerie() {
        return this.licenseSerie;
    }

    public void setLicenseSerie(String licenseSerie) {
        this.licenseSerie = licenseSerie;
    }

    public String getActivationCode() {
        return this.activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Date getActivationDate() {
        return this.activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }


    @Transient
    private Object ext;

    public Character getMaintenanceType() {
        return this.maintenanceType;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMaintenanceType(Character maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Date getMaintenanceDate() {
        return this.maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getRemoteServer() {
        return this.remoteServer;
    }

    public void setRemoteServer(String remoteServer) {
        this.remoteServer = remoteServer;
    }

    public String getRemotePass() {
        return this.remotePass;
    }

    public void setRemotePass(String remotePass) {
        this.remotePass = remotePass;
    }

    public boolean isPrinter() {
        return this.printer;
    }

    public void setPrinter(boolean printer) {
        /* 158 */ this.printer = printer;
    }

    public String getPrinterSerie() {
        /* 162 */ return this.printerSerie;
    }

    public void setPrinterSerie(String printerSerie) {
        /* 166 */ this.printerSerie = printerSerie;
    }

    public String getPrinterModel() {
        /* 170 */ return this.printerModel;
    }

    public void setPrinterModel(String printerModel) {
        /* 174 */ this.printerModel = printerModel;
    }

    public String getPrinterCode() {
        /* 178 */ return this.printerCode;
    }

    public void setPrinterCode(String printerCode) {
        /* 182 */ this.printerCode = printerCode;
    }

    public String getRemoteCode() {
        /* 186 */ return this.remoteCode;
    }

    public void setRemoteCode(String remoteCode) {
        /* 190 */ this.remoteCode = remoteCode;
    }

    public String getPrinterDescription() {
        /* 194 */ return this.printerDescription;
    }

    public void setPrinterDescription(String printerDescription) {
        /* 198 */ this.printerDescription = printerDescription;
    }

    public Character getType() {
        /* 202 */ return this.type;
    }

    public void setType(Character type) {
        /* 206 */ this.type = type;
    }

    public Integer getN3() {
        /* 210 */ return this.n3;
    }

    public String getDeviceSerie() {
        /* 214 */ return this.deviceSerie;
    }

    public void setDeviceSerie(String deviceSerie) {
        /* 218 */ this.deviceSerie = deviceSerie;
    }

    public String getDeviceModel() {
        /* 222 */ return this.deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        /* 226 */ this.deviceModel = deviceModel;
    }

    public void setN3(Integer n3) {
        /* 230 */ this.n3 = n3;
    }

    public Integer getN4() {
        /* 234 */ return this.n4;
    }

    public void setN4(Integer n4) {
        /* 238 */ this.n4 = n4;
    }

    public boolean isCanceled() {
        /* 242 */ return this.canceled;
    }

    public Date getUpdated() {
        /* 246 */ return this.updated;
    }

    public void setUpdated(Date updated) {
        /* 250 */ this.updated = updated;
    }

    public boolean isStabilizer() {
        /* 254 */ return this.stabilizer;
    }

    public void setStabilizer(boolean stabilizer) {
        /* 258 */ this.stabilizer = stabilizer;
    }

    public String getStabilizerCode() {
        /* 262 */ return this.stabilizerCode;
    }

    public void setStabilizerCode(String stabilizerCode) {
        /* 266 */ this.stabilizerCode = stabilizerCode;
    }

    public String getStabilizerDescription() {
        /* 270 */ return this.stabilizerDescription;
    }

    public void setStabilizerDescription(String stabilizerDescription) {
        /* 274 */ this.stabilizerDescription = stabilizerDescription;
    }

    public void setCanceled(boolean canceled) {
        /* 278 */ this.canceled = canceled;
    }

    public Integer getUser() {
        /* 282 */ return this.user;
    }

    public void setUser(Integer user) {
        /* 286 */ this.user = user;
    }

    public String getDescription() {
        /* 290 */ return this.description;
    }

    public void setDescription(String description) {
        /* 294 */ this.description = description;
    }

    public String getSiaf() {
        /* 298 */ return this.siaf;
    }

    public String getUserSiaf() {
        /* 302 */ return this.userSiaf;
    }

    public void setUserSiaf(String userSiaf) {
        /* 306 */ this.userSiaf = userSiaf;
    }

    public void setSiaf(String siaf) {
        /* 310 */ this.siaf = siaf;
    }

    public String getClave() {
        /* 314 */ return this.clave;
    }

    public void setClave(String clave) {
        /* 318 */ this.clave = clave;
    }

    public UtiIp() {
    }

    public Integer getDependencyId() {
        /* 328 */ return this.dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        /* 332 */ this.dependencyId = dependencyId;
    }

    public UtiIp(Integer id) {
        /* 336 */ this.id = id;
    }

    public Integer getId() {
        /* 340 */ return this.id;
    }

    public void setId(Integer id) {
        /* 344 */ this.id = id;
    }

    public String getCategory() {
        /* 348 */ return this.category;
    }

    public Integer getPeopleId() {
        /* 352 */ return this.peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        /* 356 */ this.peopleId = peopleId;
    }

    public void setCategory(String category) {
        /* 360 */ this.category = category;
    }

    public Object getExt() {
        /* 364 */ return this.ext;
    }

    public void setExt(Object ext) {
        /* 368 */ this.ext = ext;
    }

    public String getCode() {
        /* 372 */ return this.code;
    }

    public void setCode(String code) {
        /* 376 */ this.code = code;
    }

    public String getName() {
        /* 380 */ return this.name;
    }

    public void setName(String name) {
        /* 384 */ this.name = name;
    }

    public String getPosition() {
        /* 388 */ return this.position;
    }

    public void setPosition(String position) {
        /* 392 */ this.position = position;
    }

    public String getAntivirus() {
        /* 396 */ return this.antivirus;
    }

    public void setAntivirus(String antivirus) {
        /* 400 */ this.antivirus = antivirus;
    }

    public String getRestriction() {
        /* 404 */ return this.restriction;
    }

    public void setRestriction(String restriction) {
        /* 408 */ this.restriction = restriction;
    }

    public String getMacId() {
        /* 412 */ return this.macId;
    }

    public void setMacId(String macId) {
        /* 416 */ this.macId = macId;
    }

    public String getWorkgroup() {
        /* 420 */ return this.workgroup;
    }

    public void setWorkgroup(String workgroup) {
        /* 424 */ this.workgroup = workgroup;
    }

    public String getDevice() {
        /* 428 */ return this.device;
    }

    public void setDevice(String device) {
        /* 432 */ this.device = device;
    }

    public String getPatrimonialCode() {
        /* 436 */ return this.patrimonialCode;
    }

    public void setPatrimonialCode(String patrimonialCode) {
        /* 440 */ this.patrimonialCode = patrimonialCode;
    }

    public Date getCreation() {
        /* 444 */ return this.creation;
    }

    public void setCreation(Date creation) {
        /* 448 */ this.creation = creation;
    }

    public int hashCode() {
        /* 453 */ int hash = 0;
        /* 454 */ hash += (this.id != null) ? this.id.hashCode() : 0;
        /* 455 */ return hash;
    }

    public boolean equals(Object object) {
        if (!(object instanceof gob.regionancash.uti.jpa.UtiIp)) {
            return false;
        }
        gob.regionancash.uti.jpa.UtiIp other = (gob.regionancash.uti.jpa.UtiIp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "gob.regionancash.uti.jpa.UtiIp[ id=" + this.id + " ]";
    }
}